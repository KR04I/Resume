import {BaseApi} from "./BaseApi";
import {Services, ServicesControllerApi} from "./base/api";
import {configuration} from "./util/apiConfiguration";
import {customFetch} from "./util/customFetch";

class ServiceApi implements BaseApi<Services>{
    api = new ServicesControllerApi(configuration, configuration.basePath, customFetch);
    create(entity: Services): Promise<Services> {
        return this.api.createUsingPOST3(entity)
    }

    edit(id: number, entity: Services): Promise<Services> {
        return this.api.editUsingPUT3(id, entity)
    }

    findAll(): Promise<Services[]> {
        return this.api.getAllservicesUsingGET()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE3(id)
    }
}

export const serviceApi = new ServiceApi()