package com.orostock.inventory.ui;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.event.ListSelectionEvent;

import com.floreantpos.bo.ui.Command;
import com.floreantpos.bo.ui.ModelBrowser;
import com.floreantpos.bo.ui.explorer.ListTableModel;
import com.floreantpos.model.Company;
import com.floreantpos.model.dao.CompanyDAO;
import com.orostock.inventory.ui.form.CompanyEntryForm;

public class CompanyBrowser extends ModelBrowser<Company> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2457952169222336365L;

	public CompanyBrowser() {
		super(new CompanyEntryForm());
		beanEditor.clearTableModel();
		JPanel buttonPanel = new JPanel();
		this.browserPanel.add(buttonPanel, "South");
		init(new CompanyTableModel(), new Dimension(400, 400), new Dimension(500, 400));
		beanEditor.setFieldsEnable(false);
		hideDeleteBtn();
		refreshTable();
	}

	public void loadData() {
		List<Company> company = CompanyDAO.getInstance().findAll();
		CompanyTableModel tableModel = (CompanyTableModel) this.browserTable.getModel();
		tableModel.setRows(company);
	}

	public void refreshTable() {
		loadData();
		super.refreshTable();
	}

	public void refreshUITable() {
		super.refreshTable();
	}

	protected void handleAdditionaButtonActionIfApplicable(ActionEvent e) {
		if (e.getActionCommand().equalsIgnoreCase(Command.EDIT.name())) {
			beanEditor.setFieldsEnableEdit();
		}
	}

	public void valueChanged(ListSelectionEvent e) {
		super.valueChanged(e);
		beanEditor.setFieldsEnable(false);
	}

	protected void searchCompany() {
	}

	static class CompanyTableModel extends ListTableModel<Company> {
		private static final long serialVersionUID = 8008682351957964208L;

		public CompanyTableModel() {
			super(new String[] { "COMPANY NAME", "PHONE", "ADDRESS" });
		}

		public Object getValueAt(int rowIndex, int columnIndex) {
			Company row = (Company) getRowData(rowIndex);
			switch (columnIndex) {
			case 0:
				return row.getName();
			case 1:
				return row.getPhone();
			case 2:
				return row.getAddress();
			}
			return row.getName();
		}
	}
}
