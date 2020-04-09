//
//  todoListDataSource.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/09.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class todoListDataSource: NSObject, UITableViewDataSource {
    var model: [Int]!
    
    func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return model.count
    }
    
    func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "TaskCell", for: indexPath) as? TaskCardCell else { return UITableViewCell() }
        
        cell.taskTitleLabel.text = String(model[indexPath.row])
        return cell
    }
}
