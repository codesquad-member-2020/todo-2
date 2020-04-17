//
//  HistoryData.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import Foundation

struct LogData: Decodable {
    let result: Bool
    let data: ProjectLogs
    
    struct ProjectLogs: Decodable {
        let projectLogs: LogDetailData
    }
    
    struct LogDetailData: Decodable {
        let projectId: Int
        var logs: [LogDetailInfo]
    }
}
