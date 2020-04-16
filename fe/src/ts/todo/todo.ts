import { $ } from "../common/util";
import { IColumn, ICard } from "../interface/interface";

export async function getProjectData(projectId: number) {
    const options = {
        method: "GET",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
        }
    };
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}`, options);
    const result = await response.json();
    return result.data.project.categories;
}

export function renderProject(data: Array<IColumn>) {
    data.forEach(columnData => {
        renderColumn(columnData);
        const target = document.querySelector(`.card-wrap[data-column-id='${columnData.id}']`);
        columnData.cards.forEach(cardData => {
            renderCard(cardData, target);
        })
    });
}

function renderColumn(columnData: IColumn) {
    $(".content").innerHTML +=
        `<div class="column-list">
            <div class="column" data-column-id="${columnData.id}">
                <div class="column-top">
                    <div class="column-total">${columnData.cards.length}</div>
                    <div class="column-title">${columnData.title}</div>
                    <i class="material-icons column-add" data-column-id="${columnData.id}">add</i>
                    <i class="material-icons column-clear" data-column-id="${columnData.id}">clear</i>
                </div>
                <div class="card-wrap" data-column-id="${columnData.id}"></div>
            </div>
        </div>`;
}

function renderCard(cardData: ICard, target: Element) {
    const template =
        `<div class="card" data-card-id="${cardData.id}" draggable="true">
            <div class="card-top">
                <i class="material-icons card-list">list_alt</i>
                <div class="card-title">${cardData.title}</div>
                <i class="material-icons card-delete" data-card-id="${cardData.id}">close</i>
            </div>
            <div class="card-writer">Added by ${cardData.userName}</div>
        </div>`;
    target.insertAdjacentHTML("afterbegin", template);
}

export function eventHandler() {
    $(".content").addEventListener("click", clickEventDivider);
}

function clickEventDivider(event: MouseEvent) {
    if ((<HTMLElement>event.target).className.includes("column-add")) addCardDiv(<HTMLElement>event.target);
    if ((<HTMLElement>event.target).className.includes("btn-card-add")) addCard(<HTMLElement>event.target);
    if ((<HTMLElement>event.target).className.includes("card-delete")) deleteCardConfirm(<HTMLElement>event.target);
}

function deleteCardConfirm(target: HTMLElement) {
    const result = confirm("선택한 카드를 삭제하겠습니까?");
    if (result) deleteCard(target);
}

async function deleteCard(target: HTMLElement) {
    const projectId = 1; // 아직 JWT 토큰 구현 안 돼서 하드 코딩
    const categoryId = (<HTMLElement>target.closest(".column")).dataset.columnId;
    const cardId = target.dataset.cardId;
    const options = {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
        }
    }
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}/categories/${categoryId}/cards/${cardId}`, options);
    const result = await response.json();
    if (result) {
        removeCard(target.dataset.cardId);
        renderColumnTotal(categoryId);
    }
}

function removeCard(cardId: string) {
    const card = $(`.card[data-card-id='${cardId}']`);
    card.remove();
}

function renderColumnTotal(categoryId: string) {
    const length = $(`.card-wrap[data-column-id='${categoryId}']`).childElementCount;
    $(`.column[data-column-id='${categoryId}']`).querySelector(".column-total").innerText = length;
}

function addCardDiv(target: HTMLElement) {
    if ($("#add-card-wrap")) $("#add-card-wrap").remove();
    const column = document.querySelector(`.column[data-column-id='${target.dataset.columnId}']`);
    const cardWrap = document.querySelector(`.card-wrap[data-column-id='${target.dataset.columnId}']`);
    const div = document.createElement("div");
    div.id = "add-card-wrap";
    column.insertBefore(div, cardWrap);
    $("#add-card-wrap").innerHTML =
        `<div class="add-card-textarea">
            <label for="add-card-title" hidden>Title</label>
            <textarea name="title" id="add-card-title" placeholder="Enter a title for this card..."></textarea>
        </div>
        <div class="add-card-btn-wrap">
            <button class="btn-card-add" data-column-id="${target.dataset.columnId}" disabled="disabled">Add</button>
            <button class="btn-card-cancel">Cancel</button>
        </div>`;
    $("#add-card-title").addEventListener("keyup", checkAddCardTextArea);
}

function checkAddCardTextArea(event: FocusEvent) {
    if ((<HTMLTextAreaElement>event.target).value === "") {
        $(".btn-card-add").setAttribute("disabled", "disabled");
    } else {
        $(".btn-card-add").removeAttribute("disabled");
    }
}

async function addCard(target: HTMLElement) {
    const projectId = 1; // 아직 JWT 토큰 구현 안 돼서 하드 코딩
    const categoryId = target.dataset.columnId;
    const title = (<HTMLTextAreaElement>$("#add-card-title")).value;
    const options = {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
        },
        body: JSON.stringify({
            title: title,
            content: ""
        })
    }
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}/categories/${categoryId}/cards`, options);
    const result = await response.json();
}



// function dragStart(event: DragEvent) {
//     if ((<Element>event.target).className !== "card") return;
//     const previousCard = ((<Element>event.target).nextElementSibling === null) ? "card0" : (<Element>event.target).nextElementSibling.id;
//     const cards = {
//         target: (<Element>event.target).id,
//         previous: previousCard
//     }
//     event.dataTransfer.setData("text", JSON.stringify(cards));
//     event.dataTransfer.effectAllowed = "copy";
// }

// function dragEnd(event: DragEvent) {
//     event.preventDefault();
// }

// function dragOver(event: DragEvent) {
//     event.preventDefault();
// }

// function drop(event: DragEvent) {
//     if ((<Element>event.target).className !== "column-list") return;
//     console.log("drop");
//     event.preventDefault();

//     const cards = JSON.parse(event.dataTransfer.getData("text"));
//     console.log(cards);
//     // const sourceIdParentEl = element.parentElement;
//     // const targetEl = document.getElementById(event.target.id);

//     // console.log(event);
//     // event.target.appendChild(document.getElementById(id));
// }



// // document.addEventListener("drop", function (event) {
// //     event.preventDefault();
// //     if (event.target.className == "droptarget") {
// //         var data = event.dataTransfer.getData("Text");
// //         event.target.appendChild(document.getElementById(data));
// //         document.getElementById("demo").innerHTML = "The p element was dropped";
// //     }
// // });