export class CompanyExtended {
  constructor(
    public id: number|undefined,
    public name: string,
    public website: string|undefined,
    public description: string|undefined,
    public basedIn: string|undefined
    ) {
  }
}
