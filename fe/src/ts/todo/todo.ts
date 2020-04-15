import { TARGET } from "../common/target";
import { IColumn } from "../interface/interface";

export async function getTodoData(projectId: number) {
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
        `<div class="column-list">
            <div class="column" id="column${columnData.category_id}">
                <div class="column-top">
                    <div class="column-total">${columnData.cards.length}</div>
                    <div class="column-title">${columnData.title}</div>
                    <i class="material-icons column-add">add</i>
                    <i class="material-icons column-clear">clear</i>
                </div>
                <div class="add-card-wrap"></div>
                <div class="card-wrap"></div>
            </div>
        </div>`;
}

function renderCardList(columnData: IColumn) {
    columnData.cards.forEach(cardData => {
        const cardWrap = document.querySelector(`#column${columnData.category_id} > .card-wrap`);
        cardWrap.innerHTML +=
            `<div class="card" id="card${cardData.cardId}" draggable="true">
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
    TARGET.content.addEventListener("dragstart", dragStart);
    TARGET.content.addEventListener("dragend", dragEnd);
    TARGET.content.addEventListener("dragover", dragOver);
    TARGET.content.addEventListener("drop", drop);
}

function dragStart(event: DragEvent) {
    if ((<Element>event.target).className !== "card") return;
    const previousCard = ((<Element>event.target).nextElementSibling === null) ? "card0" : (<Element>event.target).nextElementSibling.id;
    const cards = {
        target: (<Element>event.target).id,
        previous: previousCard
    }
    event.dataTransfer.setData("text", JSON.stringify(cards));
    event.dataTransfer.effectAllowed = "copy";
}

function dragEnd(event: DragEvent) {
    console.log(event);
}

function dragOver(event: DragEvent) {
    event.preventDefault();
}

function drop(event: DragEvent) {
    if ((<Element>event.target).className !== "column") return;
    console.log("drop");
    event.preventDefault();

    const cards = JSON.parse(event.dataTransfer.getData("text"));
    console.log(cards);
    // const sourceIdParentEl = element.parentElement;
    // const targetEl = document.getElementById(event.target.id);

    // console.log(event);
    // event.target.appendChild(document.getElementById(id));
}



// document.addEventListener("drop", function (event) {
//     event.preventDefault();
//     if (event.target.className == "droptarget") {
//         var data = event.dataTransfer.getData("Text");
//         event.target.appendChild(document.getElementById(data));
//         document.getElementById("demo").innerHTML = "The p element was dropped";
//     }
// });