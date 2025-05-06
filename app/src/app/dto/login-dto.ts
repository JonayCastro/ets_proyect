import LoginInterface from "../interface/login-interface";

export default class LoginDTO {

    token: string;

    constructor(data: Partial<LoginInterface>) {
        this.token = data.token ?? '';
    }
}