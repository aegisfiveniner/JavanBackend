import { generateAssetStatus } from "../const/asset.config";

export class AssetConfigStatus {

  public getColor(status: string): string {
    return  generateAssetStatus.get(status.toLowerCase());
  }
}
