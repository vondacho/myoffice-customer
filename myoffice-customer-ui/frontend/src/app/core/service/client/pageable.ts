export interface Pageable<T> {
    content: Array<T>;
    totalPages: number;
    totalElements: number;
    size: number;
    number: number;
    sort: string;
    first: boolean;
}
