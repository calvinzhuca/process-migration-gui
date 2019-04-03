import React from "react";
import PropTypes from "prop-types";
import axios from "axios";

import { Button } from "patternfly-react";
import { Modal } from "patternfly-react";
import { Icon } from "patternfly-react";

import { BACKEND_URL, USE_MOCK_DATA } from "../common/PimConstants";
import PageMigrationScheduler from "../tabMigrationPlan/wizardExecuteMigration/PageMigrationScheduler";

export default class PageEditMigrationDefinitionModal extends React.Component {
  constructor(props) {
    super(props);
    this.state = {
      id: this.props.rowData.id,
      planId: this.props.rowData.definition.planId,
      processInstanceIds: this.props.rowData.definition.processInstanceIds,
      kieserverId: this.props.rowData.definition.kieserverId,
      scheduleStartTime: this.props.rowData.definition.execution
        .scheduleStartTime,
      callbackUrl: this.props.rowData.definition.execution.callbackUrl,
      showEditDialog: false
    };
  }

  convertFormDataToJson() {
    //console.log('ExecuteMigration convertFormDataToJson is triggered. ');
    const execution = {
      type: "ASYNC"
    };

    if (
      this.state.scheduleStartTime !== null &&
      this.state.scheduleStartTime !== ""
    ) {
      execution.scheduledStartTime = this.state.scheduleStartTime;
    }
    if (this.state.callbackUrl !== null && this.state.callbackUrl !== "") {
      execution.callbackUrl = this.state.callbackUrl;
    }

    const formData = {
      planId: this.state.planId,
      kieserverId: this.state.kieserverId,
      processInstanceIds: "[" + this.state.processInstanceIds + "]",
      execution: execution
    };

    var jsonStr = JSON.stringify(formData, null, 2);

    //Remove the " " from running instances because it's not a string
    if (jsonStr !== null && jsonStr !== "") {
      //replace "[ to [
      jsonStr = jsonStr.replace('"[', "[");

      //replace ]" to ]
      jsonStr = jsonStr.replace(']"', "]");
    }

    return jsonStr;
  }

  setCallbackUrl = url => {
    this.setState({
      callbackUrl: url
    });
  };

  setScheduleStartTime = startTime => {
    this.setState({
      scheduleStartTime: startTime
    });
  };

  hideEditDialog = () => {
    this.setState({ showEditDialog: false });
  };

  openEditMigration = () => {
    this.setState({
      showEditDialog: true
    });
  };

  submit = () => {
    if (USE_MOCK_DATA) {
      console.log("editMigration use mock data: ");
      this.hideEditDialog();
      //this.retriveMigrationDefinitions();
    } else {
      //need to create a temp variable "self" to store this, so I can invoke this inside axios call
      const self = this;
      const serviceUrl = BACKEND_URL + "/migrations/" + this.state.id;
      //console.log('editMigration url: ' + serviceUrl);

      const migrationDefinitionJsonStr = this.convertFormDataToJson();
      //console.log('migrationDefinitionJsonStr' + migrationDefinitionJsonStr);
      axios
        .put(serviceUrl, migrationDefinitionJsonStr, {
          headers: {
            "Content-Type": "application/json"
          }
        })
        .then(res => {
          const results = res.data;
          console.log("editMigration " + JSON.stringify(results));
          self.hideEditDialog();
          // self.retriveMigrationDefinitions();
        });
    }
  };

  submit2 = () => {
    var input = document.getElementById("planEditArea");
    var value = input.value;
    //console.log("planEditArea value: " + value);

    //could be addPlan or editPlan, the planId is only needed for editPlan
    this.props.updatePlan(value, this.props.planId);
    this.props.retrieveAllPlans();
    this.setState({ showEditDialog: false });
  };

  render() {
    const defaultBody = (
      <div>
        <div className="form-horizontal">
          <div className="form-group">
            <label className="col-md-4 control-label"> Plan ID</label>
            <div className="col-md-8">{this.state.planId}</div>
          </div>
          <div className="form-group">
            <label className="col-md-4 control-label">
              {" "}
              Process Instances ID
            </label>
            <div className="col-md-8">
              {JSON.stringify(this.state.processInstanceIds)}
            </div>
          </div>
          <div className="form-group">
            <label className="col-md-4 control-label"> KIE Server ID</label>
            <div className="col-md-8">{this.state.kieserverId}</div>
          </div>
        </div>

        <PageMigrationScheduler
          setCallbackUrl={this.setCallbackUrl}
          setScheduleStartTime={this.setScheduleStartTime}
          callbackUrl={this.state.callbackUrl}
        />
      </div>
    );

    return (
      <div>
        <Button bsStyle="link" onClick={this.openEditMigration}>
          <Icon type="fa" name="edit" />
        </Button>

        <Modal
          show={this.state.showEditDialog}
          onHide={this.hideEditDialog}
          size="lg"
        >
          <Modal.Header>
            <Modal.CloseButton onClick={this.hideEditDialog} />
            <Modal.Title>Edit Migration Definition</Modal.Title>
          </Modal.Header>
          <Modal.Body>{defaultBody}</Modal.Body>
          <Modal.Footer>
            <Button
              bsStyle="default"
              className="btn-cancel"
              onClick={this.hideEditDialog}
            >
              Cancel
            </Button>
            <Button bsStyle="primary" onClick={this.submit}>
              Submit
            </Button>
          </Modal.Footer>
        </Modal>
      </div>
    );
  }
}
