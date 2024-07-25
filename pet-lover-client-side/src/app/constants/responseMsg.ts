export interface responseMsg<T>{
    data: T;
    message: string;
    statusCode: number;
    error: string;
}