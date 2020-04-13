//
//  TodoViewController.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/08.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class TodoListViewController: UIViewController, UITableViewDelegate {

    @IBOutlet weak var cardCount: UILabel!
    @IBOutlet weak var taskCardTableView: UITableView!
    @IBOutlet weak var columnName: UILabel!
    
    let tableViewDataSource = TodoListDataSource()
    let tableViewDelegate = TodoListDelegate()
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardCountLabelSetRadius()
        
        taskCardTableView.delegate = self
        taskCardTableView.dataSource = tableViewDataSource
    
        tableViewDataSource.handler = {
            self.cardCount.text = String(self.tableViewDataSource.model!.count)
        }
    }
    
    private func cardCountLabelSetRadius() {
        cardCount.layer.masksToBounds = true
        cardCount.layer.cornerRadius = cardCount.frame.size.height/2.0
    }
}
