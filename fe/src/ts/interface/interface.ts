export interface IColumn {
    id: number,
    title: string,
    cards: Array<ICard>
}

export interface ICard {
    id: number,
    title: string,
    content: string,
    userName: string
}