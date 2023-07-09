import {useState} from "react";
import {Property} from "../Property";
import {RoomForm} from "./RoomForm";
import {Room} from "../../api/base";

interface Props {
    room: Room
    onEdit: (id?: number, room?: Room) => void
    onDelete: (id?: number) => void
}

export const RoomCard: React.FC<Props> = ({ room, onEdit, onDelete }) => {

    const [isEdit, setIsEdit] = useState(false)

    return (
        <div className="card film-card">
            {isEdit ?
                <RoomForm room={room} onSubmit={(newClient) => { onEdit(room.id, newClient); setIsEdit(false) }} />
                :
                <div className="room-card__main">

                    <Property title="Номер комнаты:" value={room.roomNumber} />
                    <Property title="Этаж:" value={room.level} />
                    <Property title="Число свободных мест:" value={room.numberAvailableSeats} />
                    <Property title="Число проживающих в комнате:" value={room.livingPeople} />
                </div>
            }
            <div className="room-card__controls">
                <button className="button" onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(room.id)}>Удалить</button>
            </div>
        </div>
    )
}