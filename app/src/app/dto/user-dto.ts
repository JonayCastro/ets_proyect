import UserInterface from "../interface/user-interface";

export default class UserDTO {

    name: string;
    password: string;


    constructor(data: Partial<UserInterface>) {
        this.name = data.name ?? '';
        this.password = data.password ?? '';
    }
}