import {BaseApi} from "./BaseApi";
import {Registration, RegistrationControllerApi} from "./base/api";
import {configuration} from "./util/apiConfiguration";
import {customFetch} from "./util/customFetch";

class RegistrationApi implements BaseApi<Registration>{
    api = new RegistrationControllerApi(configuration, configuration.basePath, customFetch);
    create(entity: Registration): Promise<Registration> {
        return this.api.createUsingPOST1(entity)
    }

    edit(id: number, entity: Registration): Promise<Registration> {
        return this.api.editUsingPUT1(id, entity)
    }

    findAll(): Promise<Registration[]> {
        //return this.api.getAllregistrationUsingGET()
        return this.api.getAllpassportUsingGET1()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE1(id)
    }
}

export const registrationApi = new RegistrationApi()