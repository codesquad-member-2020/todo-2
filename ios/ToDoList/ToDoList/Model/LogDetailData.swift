//
//  LogDetailData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

struct LogDetailInfo: Decodable {
    let user: Int
    let userName: String
    let card: Int
    let cardTitle: String
    let srcCategory: String?
    let dstCategory: String?
    let action: String
    let timeLogged: Date
}
