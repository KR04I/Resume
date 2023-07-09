import {Room} from "../../api/base";
import {useState} from "react";
import {roomApi} from "../../api/RoomApi";
import {Property} from "../Property";


interface Props {
    room?: Room
    onSubmit: (room: Room) => void
}

export const RoomForm: React.FC<Props> = ({ room, onSubmit }) => {

    //const [rooms] = useState(roomApi)

    const [roomNumber, setRoomNumber] = useState(room?.roomNumber ?? 0)
    const [level, setLevel] = useState(room?.level ?? 0)
    const [numberAvailableSeats, setNumberAvailableSeats] = useState(room?.numberAvailableSeats ?? 0)
    const [livingPeople, setLivingPeople] = useState(room?.livingPeople ?? 0)

    const onClick = () => {
        if (roomNumber === 0) return
        onSubmit({
            roomNumber,
            level,
            numberAvailableSeats,
            livingPeople
        })
        setRoomNumber(0)
        setLevel(0)
        setNumberAvailableSeats(0)
        setLivingPeople(0)
    }

    return (
        <div className="room-form">
            <Property title="Номер комнаты:"
                      value={<input type="text" value={roomNumber} onChange={e => setRoomNumber(Number(e.target.value))} />} />
            <Property title="Этаж:" value={<input type="text" value={level}
                                                   onChange={e => setLevel(Number(e.target.value))} />} />
            <Property title="Число свободных мест:"
                      value={<input type="number" value={numberAvailableSeats} onChange={e => setNumberAvailableSeats(Number(e.target.value))} />} />
            <Property title="Число проживающих в комнате:" value={<input type="text" value={livingPeople} onChange={e => setLivingPeople(Number(e.target.value))} />} />

            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}