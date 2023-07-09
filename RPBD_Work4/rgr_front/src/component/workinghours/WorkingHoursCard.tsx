import {WorkingHours} from "../../api/base";
import {WorkingHoursForm} from "./WorkingHoursForm";
import React, {useState} from "react";
import {Property} from "../Property";

interface Props {
    workingHours: WorkingHours
    onEdit: (id?: number, visitor?: WorkingHours) => void
    onDelete: (id?: number) => void
}

export const WorkingHoursCard: React.FC<Props> = ({ workingHours, onEdit, onDelete }) => {
    const [isEdit, setIsEdit] = useState(false)


    return (
        <div className="card workingHours-card">
            {isEdit ?
                <WorkingHoursForm workingHours={workingHours} onSubmit={(newClient) => { onEdit(workingHours.id, newClient); setIsEdit(false) }} />
                :
                <div className="workingHours-card__main">

                    <Property title="Дата начала работы:" value={workingHours.datestart} />
                    <Property title="Дата окончания работы:" value={workingHours.dateend} />

                    <Property title="Информация о сотруднике:" value={workingHours.staffByStaff?.name} />
                </div>
            }
            <div className="visitor-card__controls">
                <button className="button"
                        onClick={() => setIsEdit(!isEdit)}>{isEdit ? 'Закрыть' : 'Редактировать'}</button>
                <button className="button button_red" onClick={() => onDelete(workingHours.id)}>Удалить</button>
            </div>
        </div>
    )
}