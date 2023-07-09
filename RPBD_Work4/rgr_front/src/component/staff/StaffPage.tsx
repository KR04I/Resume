import {useEntities} from "../../util/useEntities";
import {staffApi} from "../../api/StaffApi";
import {useState} from "react";
import {StaffForm} from "./StaffForm";
import {StaffCard} from "./StaffCard";
import {Staff} from "../../api/base";

export const StaffPage: React.FC = () => {
    const [staffs, _, refresh] = useEntities(staffApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Staff) => {
        staffApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Staff) => {
        if (!id || !client) return
        staffApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        staffApi.delete(id).finally(refresh)
    }

    return (
        <div className="staff-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <StaffForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {staffs?.map(c => <StaffCard key={c.id} staff={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}