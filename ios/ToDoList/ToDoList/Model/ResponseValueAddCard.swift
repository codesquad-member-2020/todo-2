//
//  responseValueAddCard.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/16.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

struct ResponseValueAddCard: Decodable {
    let result: Bool
    let data: IdData
    
    struct IdData: Decodable {
        let card: Card
    }
    
    struct Card: Decodable {
        let id: Int
        let title: String
        let content: String
        let userName: String
    }
}
