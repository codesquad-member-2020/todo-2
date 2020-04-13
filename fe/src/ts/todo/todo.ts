import { TARGET } from "../common/target";
import { IColumn, ICard } from "../interface/interface";

export async function getTodoData(projectId: string) {
    const options = {
        method: "GET",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
        }
    }
    const response: Response = await fetch(`http://15.164.28.20:8080/mock/projects/${projectId}`, options);
    const result = await response.json();
    renderColumnList(result.data);
}

function renderColumnList(data: Array<IColumn>) {
    data.forEach(columnData => {
        TARGET.content.innerHTML +=
            `<div class="column" id="column${columnData.category_id}">
                <div class="column-top">
                    <div class="column-total">${columnData.cards.length}</div>
                    <div class="column-title">${columnData.title}</div>
                    <i class="material-icons column-add">add</i>
                    <i class="material-icons column-clear">clear</i>
                </div>
                <div class="add-card-wrap"></div>
                <div class="card-wrap"></div>
            </div>`;
        renderCardList(columnData);
    });
}

function renderCardList(columnData: IColumn) {
    columnData.cards.forEach(cardData => {
        const cardWrap = document.querySelector(`#column${columnData.category_id} > .card-wrap`);
        cardWrap.innerHTML +=
            `<div class="card" id="card${cardData.cardId}">
                <div class="card-top">
                    <i class="material-icons card-list">list_alt</i>
                    <div class="card-title">${cardData.title}</div>
                    <i class="material-icons card-delete">close</i>
                </div>
            <div class="card-writer">Added by ${cardData.user_name}</div>
        </div>`;
    });
}
