import { TARGET } from "../common/target";
import { IColumn } from "../interface/interface";

export async function getTodoData(projectId: string) {
    const options = {
        method: "GET",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
        }
    };
    const response: Response = await fetch(`http://15.164.28.20:8080/mock/projects/${projectId}`, options);
    const result = await response.json();
    return result.data;
}

export function renderTodo(data: Array<IColumn>) {
    data.forEach(columnData => {
        renderColumnList(columnData);
        renderCardList(columnData);
    });
}

function renderColumnList(columnData: IColumn) {
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

export function eventHandler() {

}

// function dragStart(event: any) {
//     console.log(event);
// }
// card.addEventListener("dragstart", function (event: any) {
//     console.log(event);
// });

/* Events fired on the drag target */
// document.addEventListener("dragstart", function (event) {
//     event.dataTransfer.setData("Text", event.target.id);
// });

// document.addEventListener("drag", function (event) {
//     document.getElementById("demo").innerHTML = "The p element is being dragged";
// });

// /* Events fired on the drop target */
// document.addEventListener("dragover", function (event) {
//     event.preventDefault();
// });

// document.addEventListener("drop", function (event) {
//     event.preventDefault();
//     if (event.target.className == "droptarget") {
//         var data = event.dataTransfer.getData("Text");
//         event.target.appendChild(document.getElementById(data));
//         document.getElementById("demo").innerHTML = "The p element was dropped";
//     }
// });