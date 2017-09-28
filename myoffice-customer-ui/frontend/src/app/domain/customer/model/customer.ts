export interface CustomerState {
    customer: Customer;
}

export class Customer {
    constructor(lastName: string,
                zip: string,
                city: string,
                country: string,
                firstName?: string
    ) {}
}
