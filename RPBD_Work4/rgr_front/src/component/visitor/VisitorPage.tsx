import {visitorApi} from "../../api/VisitorApi";
import {useEntities} from "../../util/useEntities";
import {useState} from "react";
import {Visitor} from "../../api/base";
import {VisitorCard} from "./VisitorCard";
import {VisitorForm} from "./VisitorForm";

export const VisitorPage: React.FC = () => {
    const [visitors, _, refresh] = useEntities(visitorApi)
    const [addFormShow, setAddFormShow] = useState(false)

    const onAddSubmit = (client: Visitor) => {
        visitorApi.create(client).finally(refresh)
        setAddFormShow(false)
    }

    const onEdit = (id?: number, client?: Visitor) => {
        if (!id || !client) return
        visitorApi.edit(id, client).finally(refresh)
    }

    const onDelete = (id?: number) => {
        if (!id) return
        visitorApi.delete(id).finally(refresh)
    }

    return (
        <div className="visitor-page">
            <div className="card">
                <button className="button" onClick={() => setAddFormShow(!addFormShow)}>{`${addFormShow ? 'Закрыть' : 'Добавить'}`}</button>
                {addFormShow &&
                    <VisitorForm onSubmit={onAddSubmit} />
                }
            </div>
            <div>
                {visitors?.map(c => <VisitorCard key={c.id} visitor={c} onEdit={onEdit} onDelete={onDelete} />)}
            </div>
        </div>
    )
}