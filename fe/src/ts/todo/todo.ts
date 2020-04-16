import { $ } from "../common/util";
import { IColumn, ICard } from "../interface/interface";

export async function getProjectData() {
    const projectId = localStorage.getItem("projectId");
    const token = localStorage.getItem("token");
    const options = {
        method: "GET",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: token
        }
    };
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}`, options);
    const result = await response.json();
    return result.data.project.categories;
}

export function renderProject(data: Array<IColumn>) {
    data.forEach(columnData => {
        renderColumn(columnData);
        columnData.cards.forEach(cardData => {
            renderCard(cardData, columnData.id);
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

function renderCard(cardData: ICard, columnId: number) {
    const template =
        `<div class="card" data-card-id="${cardData.id}" draggable="true">
            <div class="card-top">
                <i class="material-icons card-list">list_alt</i>
                <div class="card-title" data-card-id="${cardData.id}">${cardData.title}</div>
                <i class="material-icons card-delete" data-card-id="${cardData.id}">close</i>
            </div>
            <div class="card-writer">Added by ${cardData.userName}</div>
        </div>`;
    $(`.card-wrap[data-column-id='${columnId}']`).insertAdjacentHTML("afterbegin", template);
}

export function eventHandler() {
    $(".content").addEventListener("click", clickEventDivider);
    $(".content").addEventListener("dblclick", showModal);
    $("#edit-card-title").addEventListener("input", checkEditCardTextArea);
    $(".edit-card-save").addEventListener("click", editCard);
}

function clickEventDivider(event: MouseEvent) {
    if ((<HTMLElement>event.target).className.includes("column-add")) addCardDiv(<HTMLElement>event.target);
    if ((<HTMLElement>event.target).className.includes("btn-card-add")) addCard(<HTMLElement>event.target);
    if ((<HTMLElement>event.target).className.includes("card-delete")) deleteCardConfirm(<HTMLElement>event.target);
    if ((<HTMLElement>event.target).className.includes("btn-card-cancel")) deleteCardDiv();
}

function deleteCardConfirm(target: HTMLElement) {
    const result = confirm("선택한 카드를 삭제하겠습니까?");
    if (result) deleteCard(target);
}

async function deleteCard(target: HTMLElement) {
    const projectId = localStorage.getItem("projectId");
    const token = localStorage.getItem("token");
    const categoryId = (<HTMLElement>target.closest(".column")).dataset.columnId;
    const cardId = target.dataset.cardId;
    const options = {
        method: "DELETE",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: token
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

function deleteCardDiv() {
    $("#add-card-wrap").remove();
}

function addCardDiv(target: HTMLElement) {
    const column = $(`.column[data-column-id='${target.dataset.columnId}']`);
    if (column.querySelector("#add-card-wrap")) {
        deleteCardDiv();
        return;
    } else if ($("#add-card-wrap")) {
        deleteCardDiv();
    }
    const cardWrap = $(`.card-wrap[data-column-id='${target.dataset.columnId}']`);
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
    $("#add-card-title").addEventListener('input', checkAddCardTextArea);
}

function checkAddCardTextArea() {
    if ($("#add-card-title").value === "") {
        $(".btn-card-add").setAttribute("disabled", "disabled");
    } else {
        $(".btn-card-add").removeAttribute("disabled");
    }
}

async function addCard(target: HTMLElement) {
    const projectId = localStorage.getItem("projectId");
    const token = localStorage.getItem("token");
    const categoryId = target.dataset.columnId;
    const title = (<HTMLTextAreaElement>$("#add-card-title")).value;
    const options = {
        method: "POST",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: token
        },
        body: JSON.stringify({
            title: title,
            content: ""
        })
    }
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}/categories/${categoryId}/cards`, options);
    const result = await response.json();
    if (result.result) {
        renderCard(result.data.card, parseInt(categoryId));
        renderColumnTotal(categoryId);
        $("#add-card-title").value = "";
        checkAddCardTextArea();
    }
}

function showModal(event: MouseEvent) {
    if (!(<HTMLElement>event.target).className.includes("card-title")) return;
    const title = (<HTMLElement>event.target).innerText;
    const cardId = (<HTMLElement>event.target).dataset.cardId;
    const columnId = (<HTMLElement>(<Element>event.target).closest(".column")).dataset.columnId;
    $("#edit-card-title").value = title;
    $("#edit-card-title").dataset.cardId = cardId;
    $("#edit-card-title").dataset.columnId = columnId;
    toggleModal();
    $(".edit-card-close").addEventListener("click", toggleModal);
}

function toggleModal() {
    $(".edit-card-modal").classList.toggle("show-modal");
}

function checkEditCardTextArea() {
    if ($("#edit-card-title").value === "") {
        $(".edit-card-save").setAttribute("disabled", "disabled");
    } else {
        $(".edit-card-save").removeAttribute("disabled");
    }
}

async function editCard() {
    console.log("edit");
    const token = localStorage.getItem("token");
    const projectId = localStorage.getItem("projectId");
    const categoryId = $("#edit-card-title").dataset.columnId;
    const cardId = $("#edit-card-title").dataset.cardId;
    const title = (<HTMLTextAreaElement>$("#edit-card-title")).value;
    console.log(projectId, categoryId, cardId, title);
    const options = {
        method: "PUT",
        headers: {
            Accept: "application/json",
            "Content-Type": "application/json",
            "Access-Control-Allow-Origin": "*",
            Authorization: token
        },
        body: JSON.stringify({
            title: title,
            content: ""
        })
    }
    const response: Response = await fetch(`http://15.164.28.20:8080/projects/${projectId}/categories/${categoryId}/cards/${cardId}`, options);
    const result = await response.json();
    if (result) {
        toggleModal();
        $(`.card-title[data-card-id="${cardId}"`).innerText = title;
    }
}

// const editModalClose = $(".edit-card-close");
// editModalClose.addEventListener("click", closeModal);

// function closeModal() {
//     const modal = $(".edit-card-modal");
//     modal.classList.toggle("show-edit-card-modal");
// }



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