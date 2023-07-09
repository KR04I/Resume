import {BaseApi} from "./BaseApi";
import {WorkingHours, WorkingHoursControllerApi} from "./base/api";
import {configuration} from "./util/apiConfiguration";
import {customFetch} from "./util/customFetch";

class WorkingHoursApi implements BaseApi<WorkingHours>{
    api = new WorkingHoursControllerApi(configuration, configuration.basePath, customFetch);
    create(entity: WorkingHours): Promise<WorkingHours> {
        return this.api.createUsingPOST6(entity)
    }

    edit(id: number, entity: WorkingHours): Promise<WorkingHours> {
        return this.api.editUsingPUT6(id, entity)
    }

    findAll(): Promise<WorkingHours[]> {
        return this.api.getAllworkinghoursUsingGET()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE6(id)
    }
}

export const workinghoursApi = new WorkingHoursApi()