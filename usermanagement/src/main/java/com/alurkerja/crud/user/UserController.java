package com.alurkerja.crud.user;

import com.alurkerja.core.controller.CrudController;
import com.alurkerja.core.exception.AlurKerjaException;
import jakarta.validation.Valid;
import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/crud/user")
public class UserController extends CrudController<User, UserDto, UserService, UserRepository> {

    protected UserController(UserService userService) {
        super(userService);
    }

    @PostMapping("/excel")
    public ResponseEntity<Object> createFromFile(MultipartFile file) throws IOException, InvalidFormatException {
        UserFromExcel userFromExcel = new UserFromExcel();
        List<User> categories = userFromExcel.read(file.getInputStream());
        for(User user : categories){
            this.crudService.create(user);
        }
        return success(file.getOriginalFilename() + " was uploaded");
    }

    @GetMapping("/excel")
    public void exportToExcell(HttpServletResponse httpServletResponse) throws IOException {
        OutputStream outputStream = httpServletResponse.getOutputStream();

        List<User> categories = this.crudService.findAll();
        UserToExcel userToExcel = new UserToExcel(categories);
        userToExcel.export(outputStream);
        httpServletResponse.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        httpServletResponse.setHeader("Content-Disposition", "attachment;filename=\"user.xlsx\"");

        outputStream.flush();
        httpServletResponse.flushBuffer();
    }

    @PostMapping(path = "/upload")
    public ResponseEntity<Object> uploadFile(@RequestParam MultipartFile file) throws IOException {

        return this.success(file.getOriginalFilename());
    }

    @Override
    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    public ResponseEntity<Object> create(@RequestBody @Valid UserDto dto) throws AlurKerjaException {
        return super.create(dto);
    }

    @Override
    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    public ResponseEntity<Object> update(UUID id, @RequestBody @Valid UserDto dto) throws AlurKerjaException {
        return super.update(id, dto);
    }

    @Override
    @PreAuthorize("hasAnyRole ('ROLE_ADMIN')")
    public ResponseEntity<Object> delete(UUID id) throws AlurKerjaException {
        return super.delete(id);
    }
}
