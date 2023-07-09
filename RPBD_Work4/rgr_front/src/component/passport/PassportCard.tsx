import {Passport} from "../../api/base";
import React, {useState} from "react";
import {PassportForm} from "./PassportForm";
import {Property} from "../Property";

interface Props {
    passport: Passport
    onEdit: (id?: number, passport?: Passport) => void
    onDelete: (id?: number) => void
}

export const PassportCard: React.FC<Props> = ({ passport, onEdit, onDelete }) => {

    const [isEdit, setIsEdit] = useState(false)

    return (
        <div className="card film-card">
            {isEdit ?
                <PassportForm passport={passport} onSubmit={(newClient) => { onEdit(passport.id, newClient); setIsEdit(false) }} />
                :
                <div className="passport-card__main">

                    <Property title="Адрес:" value={passport.address} />
                    <Property title="Номер:" value={passport.number} />
                    <Property title="Дата выпуска:" value={passport.dateExtradition} />
                    <Property title="Выдача паспорта:" value={passport.passportIssuance} />
                </div>
            }
            <div className="passport-card__controls">
                <button className="button" onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(passport.id)}>Удалить</button>
            </div>
        </div>
    )
}