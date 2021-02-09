export class Product {
  constructor(public id: number | undefined,
              public name: string,
              public description: string | undefined,
              public price: number | undefined,
              public category: string | undefined,
              public quantity: number | undefined,
              public condition: string | undefined) {
  }
}
