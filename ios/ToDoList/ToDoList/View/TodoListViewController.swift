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
    let tableViewDelegate = self
    
    override func viewDidLoad() {
        super.viewDidLoad()
        cardCountLabelSetRadius()
        
        taskCardTableView.delegate = self
        taskCardTableView.dataSource = tableViewDataSource
    
        tableViewDataSource.handler = {
            self.cardCount.text = String(self.tableViewDataSource.model!.count)
        }
    }
    
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        guard let detailView = segue.destination as? TaskDetailViewController else { return }
        
        let cell = sender as! TaskCardCell
        let indexPath = taskCardTableView.indexPath(for: cell)
        let currentRow = (indexPath?.row)!
        
        detailView.cardDetailData = tableViewDataSource.model?[currentRow]
 }
    
    private func cardCountLabelSetRadius() {
        cardCount.layer.masksToBounds = true
        cardCount.layer.cornerRadius = cardCount.frame.size.height/2.0
    }
}
