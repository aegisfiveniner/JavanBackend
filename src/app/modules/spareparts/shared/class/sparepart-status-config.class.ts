export class SparepartStatusConfig {

  public getColor(quantity: number): string {
    let color: string;
    const lower = quantity < 3 ? 2 : null;

    switch (quantity) {
      case 0:
        color = '#E30E0E'
        break;
      case lower :
        color = '#E4AE25'
        break;
      default :
        color = '#37B744'
        break;
    }
    return color
  }

}
