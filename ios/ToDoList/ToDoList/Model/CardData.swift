//
//  cardData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/10.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

/*
protocol abc {
    var data: [CategoryData] {get set}
}
 */

struct CardData: Codable {
    var result: Bool
    var data: Project
    
    struct Project: Codable {
        var project: ProjectData
    }
    
    struct ProjectData: Codable {
               var id: Int
               var title: String
               var categories: [CategoryData]
           }
}
