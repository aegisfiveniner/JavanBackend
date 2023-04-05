export interface IAsset {
  id: number;
  assetName: string;
  serialNumber: string
  type: string
  locationId: number;
  modelNumber: string;
  manufacturer: string;
  currentStatus: string;
  downtime?: number;
}
