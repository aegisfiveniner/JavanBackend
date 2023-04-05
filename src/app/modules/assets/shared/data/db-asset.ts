import { IAsset } from '../model/asset.model';

export const DBAsset: IAsset[] = [
  {
    id: 1,
    assetName: 'Asset 01',
    serialNumber: 'Asset-01',
    type: 'Type 1',
    locationId: 1,
    modelNumber: '1111111',
    manufacturer: 'Manufacturer 1',
    currentStatus: 'Stopped',
  },
  {
    id: 2,
    assetName: 'Asset 02',
    serialNumber: 'Asset-02',
    type: 'Type 2',
    locationId: 2,
    modelNumber: '2222222',
    manufacturer: 'Manufacturer 2',
    currentStatus: 'Stopped',
  },
  {
    id: 3,
    assetName: 'Asset 03',
    serialNumber: 'Asset-03',
    type: 'Type 3',
    locationId: 3,
    modelNumber: '3333333',
    manufacturer: 'Manufacturer 3',
    currentStatus: 'Stopped',
  },
];
