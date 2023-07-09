import {BaseApi} from "./BaseApi";
import {Visitor, VisitorControllerApi} from "./base/api";
import {customFetch} from "./util/customFetch";
import {configuration} from "./util/apiConfiguration";

class VisitorApi implements BaseApi<Visitor>{
    api = new VisitorControllerApi(configuration, configuration.basePath, customFetch);

    create(entity: Visitor): Promise<Visitor> {
        return this.api.createUsingPOST5(entity)
    }

    edit(id: number, entity: Visitor): Promise<Visitor> {
        return this.api.editUsingPUT5(id, entity)
    }

    findAll(): Promise<Visitor[]> {
        //return this.api.getAllpassportUsingGET2()
        return this.api.getAllvisitorUsingGET()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE5(id)
    }
}

export const visitorApi = new VisitorApi()