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

export interface ILogs {
    action: string,
    card: number,
    cardTitle: string,
    dstCategory: string | null,
    srcCategory: string | null,
    timeLogged: string,
    user: number,
    userName: string
}