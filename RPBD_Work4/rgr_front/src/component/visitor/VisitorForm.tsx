import {Visitor} from "../../api/base";
import {useState} from "react";
import {passportApi} from "../../api/PassportApi";
import {useEntities} from "../../util/useEntities";
import {Property} from "../Property";

interface Props {
    visitor?: Visitor
    onSubmit: (visitor: Visitor) => void
}

export const VisitorForm: React.FC<Props> = ({ visitor, onSubmit }) => {

    const [passports] = useEntities(passportApi)

    const [name, setName] = useState(visitor?.name ?? '')
    const [surname, setSurname] = useState(visitor?.surname ?? '')
    const [patronymic, setPatronymic] = useState(visitor?.patronymic ?? '')
    const [birthday, setBirthday] = useState(visitor?.birthday ?? '')
    const [gender, setGender] = useState(visitor?.gender ?? 0)

    const [selectPassport, setSelectPassport] = useState<string[]>(passports?.map(o => o.id?.toString() ?? '') ?? [])


    const onClick = () => {
        if (name === '') return
        onSubmit({
            name,
            surname,
            patronymic,
            birthday,
            gender,

            passportByPassport: passports?.find(st => !!selectPassport.find(ts => ts === st.id?.toString() ?? '-1'))
        })
        setSurname('')
        setName('')
        setPatronymic('')
        setBirthday('')
        setGender(0)

        setSelectPassport([])
    }

    return (
        <div className="visitor-form">
        <Property title="Имя:" value={<input type="text" value={name} onChange={e => setName(e.target.value)} />} />
    <Property title="Фамилия:" value={<input type="text" value={surname} onChange={e => setSurname(e.target.value)} />} />
    <Property title="Отчество:" value={<input type="text" value={patronymic} onChange={e => setPatronymic(e.target.value)} />} />
    <Property title="День рождения:" value={<input type="text" value={birthday} onChange={e => setBirthday(e.target.value)} />} />
    <Property title="Пол:" value={<input type="text" value={gender} onChange={e => setGender(Number(e.target.value))} />} />

    <Property title="Информация о пасспорте:">
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