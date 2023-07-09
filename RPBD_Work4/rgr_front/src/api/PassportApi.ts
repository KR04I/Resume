import {BaseApi} from "./BaseApi";
import {Passport, PassportControllerApi} from "./base/api";
import {configuration} from "./util/apiConfiguration";
import {customFetch} from "./util/customFetch";

class PassportApi implements BaseApi<Passport>{
    api = new PassportControllerApi(configuration, configuration.basePath, customFetch);
    create(entity: Passport): Promise<Passport> {
        return this.api.createUsingPOST(entity)
    }

    edit(id: number, entity: Passport): Promise<Passport> {
        return this.api.editUsingPUT(id, entity)
    }

    findAll(): Promise<Passport[]> {
        return this.api.getAllpassportUsingGET()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE(id)
    }
}

export const passportApi = new PassportApi()