import { Country } from "@angular-material-extensions/select-country";

export class CompanyExtended {
  constructor(
    public id: number|undefined,
    public name: string,
    public website: string|undefined,
    public description: string|undefined,
    public basedIn: Country|string|undefined
    ) {
  }
}
