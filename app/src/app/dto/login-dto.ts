import LoginInterface from "../interface/login-interface";

export default class LoginDTO {

    token: string;
    url: string;

    constructor(data: Partial<LoginInterface>) {
        this.token = data.token ?? '';
        this.url = data.url ?? '';
    }
}