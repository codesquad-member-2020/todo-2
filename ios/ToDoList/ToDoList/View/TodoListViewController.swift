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
    
    
    
    private let tableViewDataSource = todoListDataSource()
    var model = [1, 2, 3, 4, 5, 6, 7]
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardCountLabelSetRadius()
        
        taskCardTableView.delegate = self
        tableViewDataSource.model = model
        taskCardTableView.dataSource = tableViewDataSource
    
        cardCount.text = String(model.count)
        NotificationCenter.default.addObserver(self,
                                               selector: #selector (reCountCardList),
                                               name: .deleteRow,
                                               object: nil)
    }
    
    private func cardCountLabelSetRadius() {
        cardCount.layer.borderColor = UIColor.black.cgColor
        cardCount.layer.borderWidth = 1.0
        cardCount.layer.masksToBounds = true
        cardCount.layer.cornerRadius = cardCount.frame.size.height/2.0
    }
    
    @objc func reCountCardList() {
        model = tableViewDataSource.model
        cardCount.text = String(model.count)
    }
}
