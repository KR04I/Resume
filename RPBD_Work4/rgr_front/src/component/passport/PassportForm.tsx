import {Passport} from "../../api/base";
import {useState} from "react";
import {passportApi} from "../../api/PassportApi";
import {Property} from "../Property";

interface Props {
    passport?: Passport
    onSubmit: (passport: Passport) => void
}

export const PassportForm: React.FC<Props> = ({ passport, onSubmit }) => {

    const [passports] = useState(passportApi)

    const [address, setAddress] = useState(passport?.address ?? '')
    const [number, setNumber] = useState(passport?.number ?? 0)
    const [dateExtradition, setDateExtradition] = useState(passport?.dateExtradition ?? '')
    const [passportIssuance, setPassportIssuance] = useState(passport?.passportIssuance ?? '')

    const onClick = () => {
        if (address === '') return
        onSubmit({
            address,
            number,
            dateExtradition,
            passportIssuance
        })
        setAddress('')
        setNumber(0)
        setDateExtradition('')
        setPassportIssuance('')
    }

    return (
        <div className="passport-form">
            <Property title="Адрес:"
                      value={<input type="text" value={address} onChange={e => setAddress(e.target.value)} />} />
            <Property title="Номер:" value={<input type="text" value={number}
                                                           onChange={e => setNumber(Number(e.target.value))} />} />
            <Property title="Дата выпуска:"
                      value={<input type="number" value={dateExtradition} onChange={e => setDateExtradition(e.target.value)} />} />
            <Property title="Выдача паспорта:" value={<input type="text" value={passportIssuance} onChange={e => setPassportIssuance(e.target.value)} />} />

            <button className="button button_green" onClick={onClick}>Ок</button>
        </div>
    )
}