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
    
    @IBAction func addNewCardButton(_ sender: Any) {
        guard let addNewCardView = self.storyboard?.instantiateViewController(withIdentifier: "TaskDetailViewController") as? TaskDetailViewController else { return }
        self.present(addNewCardView, animated: true)
        
        addNewCardView.createTask = {
            self.tableViewDataSource.cardList?.append($0)
            self.taskCardTableView.reloadData()
        }
    }
    
    let tableViewDataSource = TodoListDataSource()
    let tableViewDelegate = self
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardCountLabelSetRadius()
        
        taskCardTableView.delegate = self
        taskCardTableView.dataSource = tableViewDataSource
        
        tableViewDataSource.handler = {
            self.cardCount.text = String(self.tableViewDataSource.cardList?.count ?? 0 )
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let detailView = segue.destination as? TaskDetailViewController else { return }
        
        let cell = sender as! TaskCardCell
        let indexPath = taskCardTableView.indexPath(for: cell)
        let currentRow = (indexPath?.row)!
        
        detailView.cardDetailData = tableViewDataSource.cardList?[currentRow]
        
        detailView.editTask = {
            self.tableViewDataSource.cardList?[currentRow] = $0
            self.taskCardTableView.reloadData()
        }
    }
    
    private func cardCountLabelSetRadius() {
        cardCount.layer.masksToBounds = true
        cardCount.layer.cornerRadius = cardCount.frame.size.height/2.0
    }
}
