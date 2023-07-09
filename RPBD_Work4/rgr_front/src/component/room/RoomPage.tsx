import {roomApi} from "../../api/RoomApi";
import {RoomForm} from "./RoomForm";
import {RoomCard} from "./RoomCard";
import {useEntities} from "../../util/useEntities";
import {useState} from "react";
import {Room} from "../../api/base";

export const RoomPage: React.FC = () => {
    const [rooms, _, refresh] = useEntities(roomApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Room) => {
        roomApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Room) => {
        if (!id || !client) return
        roomApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        roomApi.delete(id).finally(refresh)
    }

    return (
        <div className="room-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <RoomForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {rooms?.map(c => <RoomCard key={c.id} room={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}