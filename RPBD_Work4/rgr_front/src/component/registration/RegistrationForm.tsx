import {Registration} from "../../api/base";
import {useState} from "react";
import {registrationApi} from "../../api/RegistrationApi";
import {useEntities} from "../../util/useEntities";
import {Property} from "../Property";
import {visitorApi} from "../../api/VisitorApi";
import {roomApi} from "../../api/RoomApi";

interface Props {
    registration?: Registration
    onSubmit: (registration: Registration) => void
}

export const RegistrationForm: React.FC<Props> = ({ registration, onSubmit }) => {

    const [visitors] = useEntities(visitorApi)
    const [rooms] = useEntities(roomApi)

    const [parkingNumber, setParkingNumber] = useState(Number(registration?.parkingNumber ?? ''))
    const [carRegistrationNumber, setCarRegistrationNumber] = useState(registration?.carRegistrationNumber ?? '')
    const [dateOfEntry, setDateOfEntry] = useState(registration?.dateOfEntry ?? '')
    const [dateOfDeparture, setDateOfDeparture] = useState(registration?.dateOfDeparture ?? '')

    const [selectVisitor, setSelectVisitor] = useState<string[]>(visitors?.map(o => o.id?.toString() ?? '') ?? [])
    const [selectRoom, setSelectRoom] = useState<string[]>(rooms?.map(o => o.id?.toString() ?? '') ?? [])


    const onClick = () => {
        if (parkingNumber === 0) return
        onSubmit({
            parkingNumber,
            carRegistrationNumber,
            dateOfEntry,
            dateOfDeparture,

            visitorByVisitor: visitors?.find(st => !!selectVisitor.find(ts => ts === st.id?.toString() ?? '-1')),
            roomByRoom: rooms?.find(st => !!selectRoom.find(ts => ts === st.id?.toString() ?? '-1'))
        })
        setParkingNumber(0)
        setCarRegistrationNumber('')
        setDateOfEntry('')
        setDateOfDeparture('')

        setSelectVisitor([])
        setSelectRoom([])
    }

    return (
        <div className="registration-form">
            <Property title="Парковочный номер:" value={<input type="text" value={parkingNumber} onChange={e => setParkingNumber(Number(e.target.value))} />} />
            <Property title="Номер машины:" value={<input type="text" value={carRegistrationNumber} onChange={e => setCarRegistrationNumber(e.target.value)} />} />
            <Property title="Дата въезда:" value={<input type="text" value={dateOfEntry} onChange={e => setDateOfEntry(e.target.value)} />} />
            <Property title="Дата выезда:" value={<input type="text" value={dateOfDeparture} onChange={e => setDateOfDeparture(e.target.value)} />} />

            <Property title="Информация о посетителе:">
                <select multiple value={selectVisitor}
                        onChange={e => setSelectVisitor(Array.from(e.target.selectedOptions, option => option.value))}>
                    {visitors?.map(m => <option key={m.id}
                                                 value={m.id}>{m.name}</option>)}
                </select>
            </Property>

                <Property title="Информация о комнате:">
                    <select multiple value={selectRoom}
                            onChange={e => setSelectRoom(Array.from(e.target.selectedOptions, option => option.value))}>
                        {rooms?.map(m => <option key={m.id}
                                                    value={m.id}>{m.roomNumber}</option>)}
                    </select>

            </Property>
            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}