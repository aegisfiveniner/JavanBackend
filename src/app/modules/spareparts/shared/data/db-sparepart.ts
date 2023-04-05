import { ISparepart } from '../model/sparepart.model';

export const DBSparepart: ISparepart[] = [
  {
    id: 1,
    sparepartName: 'Spare part 1',
    quantity: 1,
    type: 'Type 1',
    locationId: 1,
    modelNumber: '11111111',
    manufacturer: 'Manufacturer 1',
    currentStatus: 'Sufficient',
  },
  {
    id: 2,
    sparepartName: 'Spare part 2',
    quantity: 2,
    type: 'Type 2',
    locationId: 2,
    modelNumber: '22222222',
    manufacturer: 'Manufacturer 2',
    currentStatus: '< 3',
  },
  {
    id: 3,
    sparepartName: 'Spare part 3',
    quantity: 3,
    type: 'Type 3',
    locationId: 3,
    modelNumber: '33333333',
    manufacturer: 'Manufacturer 3',
    currentStatus: 'Sufficient',
  },
  {
    id: 4,
    sparepartName: 'Spare part 4',
    quantity: 4,
    type: 'Type 4',
    locationId: 4,
    modelNumber: '44444444',
    manufacturer: 'Manufacturer 4',
    currentStatus: 'Sufficient',
  },
];
