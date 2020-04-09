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
    
    private let tableViewDataSource = todoListDataSource()
    var model = [1, 2, 3, 4, 5, 6, 7] {
        didSet {
            tableViewDataSource.model = model
            taskCardTableView.reloadData()
        }
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardCountLabelSetRadius()
        
        taskCardTableView.delegate = self
        tableViewDataSource.model = model
        taskCardTableView.dataSource = tableViewDataSource
        
        setCountTaskCardList()
    }
    
    private func cardCountLabelSetRadius() {
        cardCount.layer.borderColor = UIColor.black.cgColor
        cardCount.layer.borderWidth = 1.0
        cardCount.layer.masksToBounds = true
        cardCount.layer.cornerRadius = cardCount.frame.size.height/2.0
    }
    
    func setCountTaskCardList() {
        cardCount.text = String(model.count)
    }
}
