import {Staff} from "../../api/base";
import React, {useState} from "react";
import {StaffForm} from "./StaffForm";
import {Property} from "../Property";

interface Props {
    staff: Staff
    onEdit: (id?: number, staff?: Staff) => void
    onDelete: (id?: number) => void
}

export const StaffCard: React.FC<Props> = ({ staff, onEdit, onDelete }) => {
    const [isEdit, setIsEdit] = useState(false)


    return (
        <div className="card staff-card">
            {isEdit ?
                <StaffForm staff={staff} onSubmit={(newStaff) => { onEdit(staff.id, newStaff); setIsEdit(false) }} />
                :
                <div className="staff-card__main">

                    <Property title="Фамилия:" value={staff.surname} />
                    <Property title="Имя:" value={staff.name} />
                    <Property title="Отчество:" value={staff.patronymic} />

                    <Property title="Информация о паспорте:" value={staff.passportByPassport?.number} />

                </div>
            }
            <div className="staff-card__controls">
                <button className="button"
                        onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(staff.id)}>Удалить</button>
            </div>
        </div>
    )
}