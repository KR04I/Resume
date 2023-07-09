import {Staff, Visitor} from "../../api/base";
import React, {useState} from "react";
import {Property} from "../Property";
import {VisitorForm} from "./VisitorForm";

interface Props {
    visitor: Visitor
    onEdit: (id?: number, visitor?: Visitor) => void
    onDelete: (id?: number) => void
}

export const VisitorCard: React.FC<Props> = ({ visitor, onEdit, onDelete }) => {
    const [isEdit, setIsEdit] = useState(false)


    return (
        <div className="card visitor-card">
            {isEdit ?
                <VisitorForm visitor={visitor} onSubmit={(newClient) => { onEdit(visitor.id, newClient); setIsEdit(false) }} />
                :
                <div className="visitor-card__main">

                    <Property title="Имя:" value={visitor.name} />
                    <Property title="Фамилия:" value={visitor.surname} />
                    <Property title="Отчество:" value={visitor.patronymic} />
                    <Property title="День рождения:" value={visitor.birthday} />
                    <Property title="пол:" value={visitor.gender} />

                    <Property title="Информация о паспорте:" value={visitor.passportByPassport?.number} />
                </div>
            }
            <div className="visitor-card__controls">
                <button className="button"
                        onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(visitor.id)}>Удалить</button>
            </div>
        </div>
    )
}