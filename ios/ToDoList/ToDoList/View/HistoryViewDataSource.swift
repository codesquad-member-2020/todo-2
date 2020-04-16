//
//  HistoryViewDataSource.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class HistoryViewDataSource: NSObject, UITableViewDataSource {
    
    var logData: LogData.LogDetailData?
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return logData?.logs.count ?? 0
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "historyCell", for: indexPath) as? HistoryLogCell else { return UITableViewCell() }
        cell.selectionStyle = .none
        cell.isSelected = false
        
        let logs = logData?.logs
        let action = logs![indexPath.row].action
        let title = logs![indexPath.row].cardTitle
        let dst = logs![indexPath.row].dstCategory ?? "알수없음"
        let src = logs![indexPath.row].srcCategory ?? "알수없음"
        
        switch action {
        case "added":
            cell.detailLog.text = "\(action) \(title) to \(dst)"
        case "updated":
            cell.detailLog.text = "\(action) \(title)"
        case "moved":
            cell.detailLog.text = "\(action) \(title) from \(src) to \(dst)"
        case "removed":
            cell.detailLog.text = "\(action) \(title) from \(src)"
        default:
            return UITableViewCell()
        }
        
        cell.decorateLog(title: title, srcCategory: src, dstCategory: dst)
        
        return cell
    }
    
    
}
