export interface Pageable<T> {
    content: Array<T>;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number; // returned page count
    sort: string;
    first: boolean;
}
