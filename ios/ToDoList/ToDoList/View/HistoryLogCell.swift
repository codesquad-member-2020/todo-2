//
//  HistoryLogCell.swift
//  ToDoList
//
//  Created by 임승혁 on 2020/04/17.
//  Copyright © 2020 임승혁. All rights reserved.
//

import UIKit

class HistoryLogCell: UITableViewCell {
    @IBOutlet weak var detailLog: UILabel!
    @IBOutlet weak var timeLine: UILabel!
    
    override func awakeFromNib() {
        super.awakeFromNib()
    }

    override func setSelected(_ selected: Bool, animated: Bool) {
        super.setSelected(selected, animated: animated)
    }
    
    func decorateLog(title: String, srcCategory: String, dstCategory: String) {
        let attributedStr = NSMutableAttributedString(string: detailLog.text!)
        
         attributedStr.addAttribute(.foregroundColor, value: UIColor(named: "highlightColor")!, range: (detailLog.text! as NSString).range(of: title))
        
        attributedStr.addAttribute(.foregroundColor, value: UIColor(named: "highlightColor")!, range: (detailLog.text! as NSString).range(of: srcCategory))
        
        attributedStr.addAttribute(.foregroundColor, value: UIColor(named: "highlightColor")!, range: (detailLog.text! as NSString).range(of: dstCategory))
        
        detailLog.attributedText = attributedStr
    }
}
