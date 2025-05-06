import AppConstants from "../config/app-constants";
import Paths from "../config/paths";
import { UrlType } from "../types/url-type";

export default class Utils{

    static apiPath: string = Paths.API_BASE_URL;
    static apiPort: number = Paths.API_PORT;

    static urlConstructorWithId(urlType: UrlType): string{
        const path: string = urlType.paths.join(AppConstants.URL_SEPARATOR)   
        const id: number | undefined = urlType.id

        const targetUrl: string = Utils.apiPath + 
        AppConstants.PORT_SEPARATOR + 
        Utils.apiPort + 
        AppConstants.URL_SEPARATOR +
        path +
        AppConstants.URL_SEPARATOR +
        id
        
        return targetUrl
    }

    static urlConstructorWithoutId (urlType: UrlType): string{
        const path: string = urlType.paths.join(AppConstants.URL_SEPARATOR) 

        const targetUrl: string = Utils.apiPath + 
        AppConstants.PORT_SEPARATOR + 
        Utils.apiPort + 
        AppConstants.URL_SEPARATOR +
        path

        return targetUrl
    }
}