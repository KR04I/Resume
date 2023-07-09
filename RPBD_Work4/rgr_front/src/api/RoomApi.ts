import {BaseApi} from "./BaseApi";
import {Room, RoomControllerApi} from "./base/api";
import {configuration} from "./util/apiConfiguration";
import {customFetch} from "./util/customFetch";

class RoomApi implements BaseApi<Room>{
    api = new RoomControllerApi(configuration, configuration.basePath, customFetch);
    create(entity: Room): Promise<Room> {
        return this.api.createUsingPOST2(entity)
    }

    edit(id: number, entity: Room): Promise<Room> {
        return this.api.editUsingPUT2(id, entity)
    }

    findAll(): Promise<Room[]> {
        return this.api.getAllroomUsingGET()
    }

    delete(id: number): Promise<Response> {
        return this.api.deleteUsingDELETE2(id)
    }
}

export const roomApi = new RoomApi()