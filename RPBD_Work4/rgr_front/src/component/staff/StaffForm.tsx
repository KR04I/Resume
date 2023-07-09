import {Staff} from "../../api/base";
import {useEntities} from "../../util/useEntities";
import {passportApi} from "../../api/PassportApi";
import React, {useState} from "react";
import {Property} from "../Property";

interface Props {
    staff?: Staff
    onSubmit: (staff: Staff) => void
}

export const StaffForm: React.FC<Props> = ({ staff, onSubmit }) => {

    const [passports] = useEntities(passportApi)

    const [name, setName] = useState(staff?.name ?? '')
    const [surname, setSurname] = useState(staff?.surname ?? '')
    const [patronymic, setPatronymic] = useState(staff?.patronymic ?? '')

    const [selectPassport, setSelectPassport] = useState<string[]>(passports?.map(o => o.id?.toString() ?? '') ?? [])




    const onClick = () => {
        if (surname === '') return
        onSubmit({
            surname,
            name,
            patronymic,

            passportByPassport: passports?.find(st => !!selectPassport.find(ts => ts === st.id?.toString() ?? '-1'))

            //staffByStaffId: staff?.find(st => !!selectStaff.find(ts => ts === st.id?.toString() ?? '-1')),
        })
        setSurname('')
        setName('')
        setPatronymic('')
        setSelectPassport([])
    }

    return (
        <div className="staff-form">
            <Property title="Имя:" value={<input type="text" value={name} onChange={e => setName(e.target.value)} />} />
            <Property title="Фамилия:" value={<input type="text" value={surname} onChange={e => setSurname(e.target.value)} />} />
            <Property title="Отчество:" value={<input type="text" value={patronymic} onChange={e => setPatronymic(e.target.value)} />} />

            <Property title="Информация о паспорте:">
                <select multiple value={selectPassport}
                        onChange={e => setSelectPassport(Array.from(e.target.selectedOptions, option => option.value))}>
                    {passports?.map(m => <option key={m.id}
                                                          value={m.id}>{m.address}</option>)}
                </select>
            </Property>
            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}